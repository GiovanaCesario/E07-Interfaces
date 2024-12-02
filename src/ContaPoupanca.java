public class ContaPoupanca extends Conta {
    public ContaPoupanca(int numero, Cliente dono, double saldo, double limite) {
        super(numero, dono, saldo, limite);
    }

    @Override
    public void setLimite(double limite) {
        if (limite <= 1000 && limite >= 100)
            super.limite = limite;
    }

    @Override
    public double cauculaTaxas() {
        return 0;
    }
}

