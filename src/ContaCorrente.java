public class ContaCorrente extends Conta {

    public ContaCorrente(int numero, Cliente dono, double saldo, double limite) {
        super(numero, dono, saldo, limite);
    }

    @Override
    public void setLimite(double limite) {
        if (limite >= -100)
            super.limite = limite;
    }

    @Override
    public double cauculaTaxas() {
        return getDono().cauculaTaxas();
    }
}
