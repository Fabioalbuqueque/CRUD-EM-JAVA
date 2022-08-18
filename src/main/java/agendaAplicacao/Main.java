package agendaAplicacao;

import AgendaModel.Contato;
import agendaDao.ContatoDAO;

import java.util.Date;
import agendaFactory.ConnectionFactory;

public class Main {

    public static void main(String[] args) {

        ContatoDAO contatoDao = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("KETELLYN");
        contato.setIdade(20);
        contato.setDataCadastro(new Date());

         //contatoDao.save(contato);

        //Atualizar o contato

        Contato c1 = new Contato();
        c1.setNome("MARIA LUCIA");
        c1.setIdade(32);
        c1.setDataCadastro(new Date());
        c1.setId(1); // é o numero que esta no banco de dados da PK

        contatoDao.update(c1);









        //Visualização dos registros do banco de dados

        for(Contato c : contatoDao.getContatos()) {
            System.out.println("Contact: "+c.getNome());
        }
    }

}


