package challenge;

import java.util.*;


public class Estacionamento {
    private final static int NUMERO_VAGAS = 10;
    private final static int IDADE_PREFERENCIAL = 55;
    private final LinkedList<Carro> carros;

    Estacionamento() {
        this.carros = new LinkedList<>();
    }

    public void estacionar(Carro carro) {
        this.validarMotorista(carro.getMotorista());

        if (this.carrosEstacionados() == NUMERO_VAGAS) {
            for (Iterator<Carro> i = carros.iterator(); i.hasNext(); ) {
                if (i.next().getMotorista().getIdade() <= IDADE_PREFERENCIAL) {
                    i.remove();
                    break;
                }
            }
        }

        if (this.carrosEstacionados() == NUMERO_VAGAS) throw new EstacionamentoException("Estacionamento lotado");
        carros.add(carro);
    }

    private void validarMotorista(Motorista motorista) {
        if (motorista == null) throw new EstacionamentoException("Carro autonomo nao permitido");
        if (motorista.getIdade() < 18) throw new EstacionamentoException("Motorista de menor");
        if (motorista.getPontos() > 20) throw new EstacionamentoException("Habilitacao suspensa");
    }

    public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carros.contains(carro);
    }
}
