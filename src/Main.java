import java.util.Date;

public class Main {
    public static void main(String[] args) {

        Cliente joao = new PessoaFisica("João", "Av. Antonio Carlos, 6627",
                new Date(), "111.111.111-11", 36, 'm');

        Cliente lojinha = new PessoaJuridica("Loja R$1,99", "Av. Afonso Pena, 3000",
                new Date(), "000.00000.0000/0001", 25, "Comércio");


        Conta conta1 = new ContaCorrente(1234, joao, 0, 1500);
        Conta conta2 = new ContaPoupanca(12121, lojinha, 10000, 1500);
        Conta conta3 = new ContaCorrente(23234, lojinha, 345, 1000);

        conta1.depositar(3000);
        conta1.depositar(2000);
        conta1.sacar(500);
        conta1.sacar(222);

        conta2.transferir(conta1, 666);
        conta2.depositar(234);
        conta2.sacar(33);
        conta2.sacar(22);
        conta2.depositar(333);

        conta3.sacar(200);
        conta3.depositar(10000);
        conta3.sacar(23);

        conta1.imprimirExtratoTaxas();
        conta2.imprimirExtratoTaxas();
        conta3.imprimirExtratoTaxas();
    }
}