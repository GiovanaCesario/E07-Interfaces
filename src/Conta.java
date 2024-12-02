public abstract class Conta implements ITaxas{

    private int numero;

    private Cliente dono;

    private double saldo;

    protected double limite;

    private Operacao[] operacoes;

    private int proximaOperacao;

    private static int totalContas = 0;;

    public Conta(int numero, Cliente dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        this.limite = limite;

        this.operacoes = new Operacao[1000];
        this.proximaOperacao = 0;

        Conta.totalContas++;
    }

    public boolean sacar(double valor) {
        if (valor >= 0 && valor <= this.limite) {
            this.saldo -= valor;

            this.operacoes[proximaOperacao] = new OperacaoSaque(valor);
            this.proximaOperacao++;
            return true;
        }

        return false;
    }

    public void depositar(double valor) {
        this.saldo += valor;

        this.operacoes[proximaOperacao] = new OperacaoDeposito(valor);
        this.proximaOperacao++;
    }

    public boolean transferir(Conta destino, double valor) {
        boolean valorSacado = this.sacar(valor);
        if (valorSacado) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return dono.toString() + '\n' +
                "---\n" +
                "numero = " + numero + '\n' +
                "saldo = " + saldo + '\n' +
                "limite = " + limite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof Conta) {
            Conta conta = (Conta) o;
            return numero == conta.numero;
        }
        return false;
    }

    public void imprimirExtrato() {
        System.out.println("======= Extrato Conta " + this.numero + "======");
        for(Operacao atual : this.operacoes) {
            if (atual != null) {
                System.out.println(atual);
            }
        }
        System.out.println("====================");
    }

    public void imprimirExtratoTaxas() {
        double totalTaxas = 0;

        System.out.println("=== Extrato de Taxas " + this.numero + " ===");
        System.out.printf("Manutenção da conta: %.2f\n", this.cauculaTaxas());
        totalTaxas += this.cauculaTaxas();

        for(Operacao atual : this.operacoes) {

            //Imprime somente as operacoes com taxa significativa
            if(atual != null && atual.cauculaTaxas() != 0) {

                if(atual.getTipo() == 'd') {
                    System.out.printf("Deposito: %.2f\n",atual.cauculaTaxas());
                    totalTaxas += atual.cauculaTaxas();

                } else if (atual.getTipo() == 's'){
                    System.out.printf("Saque: %.2f\n", atual.cauculaTaxas());
                    totalTaxas += atual.cauculaTaxas();
                }
            }
        }
        System.out.printf("Total: %.2f\n", totalTaxas);
        System.out.println("==============================");
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public static int getTotalContas() {
        return Conta.totalContas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public abstract void setLimite(double limite);
}
