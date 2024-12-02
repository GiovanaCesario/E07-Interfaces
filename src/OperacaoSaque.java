public class OperacaoSaque extends Operacao {

    public OperacaoSaque(double valor) {
        super('s', valor);
    }

    @Override
    public double cauculaTaxas() {
        return 0.05;
    }
}
