package Projeto1;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

class Cliente {
    private String nome;
    private String telefone;
    private String email;
    private List<Pet> pets = new ArrayList<>();

    public Cliente(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() { return nome; }
    public List<Pet> getPets() { return pets; }
    public void adicionarPet(Pet pet) { pets.add(pet); }
    public void removerPet(Pet pet) { pets.remove(pet); }

    @Override
    public String toString() {
        return nome + " - " + telefone + " - " + email;
    }
}

class Pet {
    private String nome;
    private String especie;
    private String raca;
    private int idade;
    private double peso;

    public Pet(String nome, String especie, String raca, int idade, double peso) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.idade = idade;
        this.peso = peso;
    }

    public String getNome() { return nome; }

    @Override
    public String toString() {
        return nome + " (" + especie + ", " + raca + ", " + idade + " anos, " + peso + "kg)";
    }
}

abstract class Servico {
    protected String descricao;
    protected Date data;

    public Servico(String descricao, Date data) {
        this.descricao = descricao;
        this.data = data;
    }

    public abstract double getPreco();

    @Override
    public String toString() {
        return descricao + " no dia " + data;
    }
}

class BanhoETosa extends Servico {
    public BanhoETosa(Date data) {
        super("Banho e Tosa", data);
    }

    @Override
    public double getPreco() {
        return 50.0;
    }
}

class ConsultaVeterinaria extends Servico {
    public ConsultaVeterinaria(Date data) {
        super("Consulta Veterinária", data);
    }

    @Override
    public double getPreco() {
        return 100.0;
    }
}

class PacoteServicos {
    private List<Servico> servicos = new ArrayList<>();

    public void adicionarServico(Servico s) {
        servicos.add(s);
    }

    public double getPrecoTotal() {
        double total = 0;
        for (Servico s : servicos) {
            total += s.getPreco();
        }
        return total * 0.9; // 10% de desconto
    }

    public List<Servico> getServicos() { return servicos; }

    @Override
    public String toString() {
        return "Pacote: " + servicos + ", Total com desconto: R$" + getPrecoTotal();
    }
}

public class SistemaPetShop {

    // NOVO: Método para agendar data manualmente
    public static Date solicitarDataHoraServico() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, "Digite a data e hora do serviço (ex: 25/04/2025 14:30):");
                if (input == null) return new Date(); // caso o usuário cancele
                LocalDateTime dataHora = LocalDateTime.parse(input, formatter);
                return Date.from(dataHora.atZone(ZoneId.systemDefault()).toInstant());
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato inválido! Use: dd/MM/yyyy HH:mm", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente("Gabriel", "(61)99848-2810", "gabrielbigorna969@gamil.com");
        Pet pet = new Pet("Ozzy", "Cachorro", "Dog Alemao", 3, 70.0);
        cliente.adicionarPet(pet);

        Date dataAgendada = solicitarDataHoraServico();
        Servico banho = new BanhoETosa(dataAgendada);
        Servico consulta = new ConsultaVeterinaria(solicitarDataHoraServico());

        PacoteServicos pacote = new PacoteServicos();
        pacote.adicionarServico(banho);
        pacote.adicionarServico(consulta);

        System.out.println("Cliente: " + cliente);
        System.out.println("Pet: " + pet);
        System.out.println(pacote);

        Cliente cliente1 = new Cliente("Carol", "(61)99848-2810", "carolpsico@gamil.com");
        Pet pet1 = new Pet("Biluca", "Cachorro", "Vira lata", 5, 15.0);
        cliente.adicionarPet(pet1);

        Servico banho1 = new BanhoETosa(solicitarDataHoraServico());
        PacoteServicos pacote1 = new PacoteServicos();
        pacote1.adicionarServico(banho1);

        System.out.println("Cliente: " + cliente1);
        System.out.println("Pet: " + pet1);
        System.out.println(pacote1);

        Cliente cliente2 = new Cliente("Angelica", "(61)99848-2810", "Angelica-avila@hotmail.com");
        Pet pet2 = new Pet("Cherie", "Cachorro", "Vira lata", 7, 4.0);
        cliente.adicionarPet(pet2);

        Servico banho2 = new BanhoETosa(solicitarDataHoraServico());
        PacoteServicos pacote2 = new PacoteServicos();
        pacote2.adicionarServico(banho2);

        System.out.println("Cliente: " + cliente2);
        System.out.println("Pet: " + pet2);
        System.out.println(pacote2);

        Cliente cliente3 = new Cliente("Gessica", "(61)99848-2810", "gessica-siqueira@hotmail.com");
        Pet pet3 = new Pet("Pink", "Cachorro", "Pinsher", 6, 3.0);
        cliente.adicionarPet(pet3);

        Servico banho3 = new BanhoETosa(solicitarDataHoraServico());
        PacoteServicos pacote3 = new PacoteServicos();
        pacote3.adicionarServico(banho3);

        System.out.println("Cliente: " + cliente3);
        System.out.println("Pet: " + pet3);
        System.out.println(pacote3);

        Cliente cliente4 = new Cliente("Camila", "(61)99848-2810", "camila-rios@hotmail.com");
        Pet pet4 = new Pet("Atila", "Cachorro", "Golden Retriver", 2, 18.0);
        cliente.adicionarPet(pet4);

        Servico banho4 = new BanhoETosa(solicitarDataHoraServico());
        PacoteServicos pacote4 = new PacoteServicos();
        pacote4.adicionarServico(banho4);

        System.out.println("Cliente: " + cliente4);
        System.out.println("Pet: " + pet4);
        System.out.println(pacote4);
    }
}

