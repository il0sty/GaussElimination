package MetodoDeGauss;
import java.util.ArrayList;
import java.util.Scanner;

public class Visao {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite o numero de linhas/colunas: ");
        int n = input.nextInt();
        boolean impossible = false;
        double[][] matriz = new double[n][n];
        double[] result = new double[n];
        for(int x = 0; x<n; x++){
            for(int y = 0; y<n; y++){
                System.out.print("Digite o valor da posição "+(x+1)+"/"+(y+1)+":");
                matriz[x][y] = input.nextInt();
            }
            System.out.print("Digite o valor referente ao resultado da "+(x+1)+"ª linha do sistema: ");
            result[x] = input.nextInt();
        }
        System.out.println("\n");
        System.out.println("\n Matriz Pré Escalonamento");
        for(int x = 0; x<n; x++){
            for(int y = 0; y<n; y++){
                System.out.print(matriz[x][y] + " , ");
            }
            System.out.print(+result[x]+"\n");
        }
        int linhaTroca = 0;
        for (int x = 0; x<n; x++){
            for (int y = 0; y<=x; y++) {
                System.out.println("\n");
                if (x == y && matriz[x][y] == 0) {
                    for (int z = x; z < n; z++) {
                        if (matriz[z][y] == 1){
                            linhaTroca=z;
                            break;
                        }
                    }
                    if(matriz[linhaTroca][y]!=0){
                        System.out.println("Trocando a linha "+(x+1)+" com a linha "+(linhaTroca+1)+"\n");
                        double saveTroca;
                        for(int t = 0; t<n; t++){
                            saveTroca = matriz[x][t];
                            matriz[x][t]=matriz[linhaTroca][t];
                            matriz[linhaTroca][t] = saveTroca;
                        }
                        saveTroca = result[x];
                        result[x] = result[linhaTroca];
                        result[linhaTroca] = saveTroca;

                        int pivoPosicao = y;
                        if(y != 0){
                            pivoPosicao = y-1;
                        }
                        double valor = matriz[x][0] / matriz[pivoPosicao][pivoPosicao];
                        for (int z = 0; z < n; z++) {
                            double percorrerLinha = matriz[x][z];
                            double linhaBase = matriz[pivoPosicao][z];
                            double rslt = percorrerLinha - (linhaBase * valor);
                            matriz[x][z] = rslt;
                        }
                        result[x] = result[x] - result[pivoPosicao] * valor;
                        for(int p = 0; p<n; p++){
                            for(int q = 0; q<n; q++) {
                                System.out.print(String.format("%.2f",matriz[p][q]) + " , ");
                            }
                            System.out.print(String.format("%.2f",result[p])+ "\n");
                        }

                    }
                    else impossible=true;
                }

                if(x!=y){
                    double valor = matriz[x][y] / matriz[y][y];
                    System.out.println("Multiplicando a linha "+(y+1)+" por "+valor+" e subtraindo da linha "+(x+1)+"\n");
                    for (int z = 0; z < n; z++) {
                        double percorrerLinha = matriz[x][z];
                        double linhaBase = matriz[y][z];
                        double rslt = percorrerLinha - (linhaBase * valor);
                        matriz[x][z] = rslt;
                    }
                    result[x] = result[x] - result[y] * valor;
                    for(int p = 0; p<n; p++){
                        for(int q = 0; q<n; q++) {
                            System.out.print(String.format("%.2f",matriz[p][q]) + " , ");
                        }
                        System.out.print(String.format("%.2f",result[p])+ "\n");
                    }
                }
            }


            if (impossible){
                break;
            }

        }

        if(impossible){
            System.out.println("Esse sistema não possui solução ou a única solução possível é 0!");
        } else {
            System.out.println("Matriz Pós Escalonamento via Método de Gauss-Jordan:\n");
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    System.out.print(String.format("%.2f", matriz[x][y]) + " , ");
                }
                System.out.print(String.format("%.2f", result[x]) + "\n");
            }
            ArrayList <Double> respostas = new ArrayList<>();

            for (int i = 0; i<n;i++){
                respostas.add((double) 0);
            }

            for (int i = n-1; i>=0; i--){
                if(i == n-1){
                    respostas.set(i,result[i]/matriz[i][i]);
                } else {
                    double soma = 0;
                    for (int j = n-1; j>i; j--){
                        soma += respostas.get(j)*matriz[i][j];
                    }
                    double valorAdd = (result[i]-soma)/matriz[i][i];
                    respostas.set(i,valorAdd);
                }
            }
            System.out.println("\nRespostas:\n");
            for (int i = 0; i<n; i++){
                System.out.println("x"+(i+1)+": "+String.format("%.2f",respostas.get(i)));
            }
        }
    }

}
