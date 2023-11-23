import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Atendimento {
    private LocalDate data;
    private String descricao;

    public Atendimento(LocalDate data, String descricao) {
        this.data = data;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Data: " + data + ", Descrição: " + descricao;
    }
}

class Paciente {
    private String nome;
    private LocalDate dataNascimento;
    private ArrayList<Atendimento> atendimentos;
    private boolean ativo;

    public Paciente(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.atendimentos = new ArrayList<>();
        this.ativo = true;
    }

    public void adicionarAtendimento(LocalDate data, String descricao) {
        Atendimento atendimento = new Atendimento(data, descricao);
        atendimentos.add(atendimento);
    }

    public void desativarPaciente() {
        this.ativo = false;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public ArrayList<Atendimento> getAtendimentos() {
        return atendimentos;
    }

    public String getNome() {
        return nome;
    }

1    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Paciente paciente = (Paciente) obj;
        return nome.equals(paciente.nome);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Data de Nascimento: " + dataNascimento + ", Ativo: " + ativo;
    }
}

public class SistemaConsultaMedica {
    private ArrayList<Paciente> pacientes;
    private Scanner scanner;

    public SistemaConsultaMedica() {
        this.pacientes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void incluirPaciente() {
        System.out.print("Nome do paciente: ");
        String nome = scanner.nextLine();
        System.out.print("Data de nascimento (AAAA-MM-DD): ");
        LocalDate dataNascimento = LocalDate.parse(scanner.nextLine());
        Paciente paciente = new Paciente(nome, dataNascimento);
        pacientes.add(paciente);
        System.out.println("Paciente adicionado com sucesso!");
    }

    public void alterarPaciente() {
        System.out.print("Digite o nome do paciente para alteração: ");
        String nome = scanner.nextLine();
        Paciente paciente = buscarPaciente(nome);
        if (paciente != null) {
            // Realizar alterações necessárias
            System.out.println("Paciente encontrado. Faça as alterações desejadas.");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    public void realizarAtendimento() {
        System.out.print("Digite o nome do paciente para realizar o atendimento: ");
        String nome = scanner.nextLine();
        Paciente paciente = buscarPaciente(nome);
        if (paciente != null && paciente.isAtivo()) {
            System.out.print("Data do atendimento (AAAA-MM-DD): ");
            LocalDate dataAtendimento = LocalDate.parse(scanner.nextLine());
            System.out.print("Descrição do atendimento: ");
            String descricaoAtendimento = scanner.nextLine();
            paciente.adicionarAtendimento(dataAtendimento, descricaoAtendimento);
            System.out.println("Atendimento realizado com sucesso!");
        } else {
            System.out.println("Paciente não encontrado ou inativo.");
        }
    }

    public void listarPacientes() {
        System.out.println("Lista de Pacientes Ativos:");
        for (Paciente paciente : pacientes) {
            if (paciente.isAtivo()) {
                System.out.println(paciente);
            }
        }
    }

    public void mostrarPaciente() {
        System.out.print("Digite o nome do paciente para mostrar detalhes: ");
        String nome = scanner.nextLine();
        Paciente paciente = buscarPaciente(nome);
        if (paciente != null && paciente.isAtivo()) {
            System.out.println("Detalhes do Paciente:");
            System.out.println(paciente);
            System.out.println("Atendimentos:");
            ArrayList<Atendimento> atendimentos = paciente.getAtendimentos();
            int contador = 0;
            for (Atendimento atendimento : atendimentos) {
                System.out.println(atendimento);
                contador++;
                if (contador % 5 == 0 && contador != atendimentos.size()) {
                    System.out.print("Pressione Enter para continuar...");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("Paciente não encontrado ou inativo.");
        }
    }


    public void apagarPaciente() {
        System.out.print("Digite o nome do paciente para apagar: ");
        String nome = scanner.nextLine();
        Paciente paciente = buscarPaciente(nome);
        if (paciente != null) {
            paciente.desativarPaciente();
            System.out.println("Paciente removido com sucesso!");
        } else {
            System.out.println("Paciente não encontrado.");
        }
    }

    private Paciente buscarPaciente(String nome) {
        for (Paciente paciente : pacientes) {
            if (paciente.getNome().equals(nome)) {
                return paciente;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SistemaConsultaMedica sistema = new SistemaConsultaMedica();
        int opcao;
        do {
            System.out.println("1. Incluir Paciente");
            System.out.println("2. Alterar Paciente");
            System.out.println("3. Realizar Atendimento");
            System.out.println("4. Listar Pacientes");
            System.out.println("5. Mostrar Paciente");
            System.out.println("6. Apagar Paciente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sistema.scanner.nextInt();
            sistema.scanner.nextLine(); // Consumir a quebra de linha pendente
            switch (opcao) {
                case 1:
                    sistema.incluirPaciente();
                    break;
                case 2:
                    sistema.alterarPaciente();
                    break;
                case 3:
                    sistema.realizarAtendimento();
                    break;
                case 4:
                    sistema.listarPacientes();
                    break;
                case 5:
                    sistema.mostrarPaciente();
                    break;
                case 6:
                    sistema.apagarPaciente();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
        sistema.scanner.close();
    }
}
