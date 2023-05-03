package main;

import Modelo.Produto;
import Utils.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mercado {
    private static Scanner entrada = new Scanner(System.in);
    private static ArrayList<Produto> produtos;
    private static Map<Produto, Integer> carrinho;

    public static void main(String[] args) {
        produtos = new ArrayList<>();
        carrinho = new HashMap<>();
        menu();
    }

    public static void menu () {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("------------------------- WELCOME TO ARAUJO´S MARKET --------------------");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("****************** SELECIONE QUE OPERAÇÃO DESEJA FAZER ******************");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("|  Opção 1 - Cadastrar |");
        System.out.println("|  Opção 2 - Listar     |");
        System.out.println("|  Opção 3 - Comprar    |");
        System.out.println("|  Opção 4 - Carrinho   |");
        System.out.println("|  Opção 5 - Sair       |");

        int esco = entrada.nextInt();
        switch (esco){
            case 1 :
                cadastrarProdutos();
                break;

            case 2 :
                listarProdutos();
                break;

            case 3 :
                compraProdutos();
                break;
            case 4 :
                verCarrinho();
                break;
            case 5 :
                System.out.println("-------------------------------------------------------------------------");
                System.out.println("---------- ATÉ MAIS, ARAUJO´S MARKET AGRADEÇE A PREFERÊNCIA ;D ----------");
                System.out.println("-------------------------------------------------------------------------");
                System.exit(0);
            default:
                System.out.println("EPA ESSA OPÇÃO É INVALIDA ;D");
                menu();
                break;

        }



    }
    private static void cadastrarProdutos() {
        System.out.println("Qual o nome do produto: ");
        String nome = entrada.next();

        System.out.println("Qual o preço do produto: ");
        Double preco = entrada.nextDouble();

        Produto produto = new Produto(nome, preco);
        produtos.add(produto);

        System.out.println(produto.getNome() + " casdastrado com sucesso!! \n ");
        menu();
    }

    private static void listarProdutos() {
        if (produtos.size() > 0) {
            System.out.println("---------- LISTA DE PRODUTOS: ---------- \n");
            for (Produto p : produtos) {
                System.out.println(p);
                System.out.println("\n");
            }

        } else {
            System.out.println(" Nenhum Produto Cadastrado \n");
        }

        menu();
    }

    private static void compraProdutos() {
        if (produtos.size() > 0) {

            System.out.println("---------- Produtos Disponíveis ---------- \n");
            for (Produto p : produtos) {
                System.out.println(p + "\n");

            }
            System.out.println("Código do produto: \n");
            int id = Integer.parseInt(entrada.next());
            boolean isPresente = false;

            for (Produto p : produtos) {
                if (p.getId() == id) {
                    int qtd = 0;
                    try { //
                        qtd = carrinho.get(p);
                        //se o produto ja estiver no carrinho incrementar a quantidade
                        carrinho.put(p, qtd + 1);
                    }catch (NullPointerException e){
                        // se o estiver sendo adcionado pela primeira vez
                        carrinho.put(p, 1);
                    }
                    System.out.println(p.getNome() + " Adcionado ao carrinho.");
                    isPresente = true;

                    if (isPresente) {
                        System.out.println("Deseja comtinuar comprando? ");
                        System.out.println("Digite 1 para continuar comprando ou 0 para finalizar compra atual: ");
                        int esco = Integer.parseInt(entrada.next());
                        if (esco == 1) {
                            compraProdutos();
                        } else if (esco == 0 ) {
                            finalizarCompra();
                        }

                    }
                } else {
                    System.out.println("Esse Produto Não foi Encontrado");
                    menu();
                }

            }
        } else {
            System.out.println("Este produto não esta cadastrado no nosso mercado");
            menu();
        }

    }

    private static void verCarrinho(){
        System.out.println("---------- Produtos no seu carrinho ---------- \n");
        if (carrinho.size() > 0){
            for (Produto p : carrinho.keySet()) {
                System.out.println("Produto: " + p + "\nQuantidade: " + carrinho.get(p));
            }
        } else {
            System.out.println("---------- CARRINHO VAZIO ---------- \n");
        }
        menu();
    }

    private static void finalizarCompra(){
        double valorCompra = 0.0;
        System.out.println("Seus Produtos: ");

        for (Produto p : carrinho.keySet()) {
            int qtd = carrinho.get(p);
            valorCompra += p.getPreco() * qtd;
            System.out.println("------------------------");
            System.out.println("| "    + p  +        " |");
            System.out.println("| Quantidade: "+ qtd +"|");
            System.out.println("------------------------");
            System.out.println("\n");

        }
        System.out.println("O valor da sua Compra é de: " + utils.doubleToString(valorCompra));
        carrinho.clear();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("---------- ATÉ MAIS, ARAUJO´S MARKET AGRADEÇE A PREFERÊNCIA ;D ----------");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("\n");
        menu();
    }



}
