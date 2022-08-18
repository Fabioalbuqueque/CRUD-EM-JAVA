package agendaDao;

import AgendaModel.Contato;
import agendaFactory.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


//import br.com.agenda.factory.ConnectionFactory;
//import br.com.agenda.model.Contato;


public class ContatoDAO {

    /*
     * CRUD c: CREATE - OK - INSERT r: READ - SELECT u: UPDATE - UPDATE d: DELETE -
     * DELETE
     */
//.....................................................................SAVE.........................................................................\\
    public void save(Contato contato) {

        String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // Criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionFirebird();

            // Criamos uma PreparedStatement, para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            // Adicionar os valores que são esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            // Executar a query
            pstm.execute();
            System.out.println("Contato salvo com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // Fechar as conexões
            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //...............................................................................UPDATE.....................................................................\\
    public void update(Contato contato) {


        String sql = "UPDATE  contatos SET nome = ? , idade = ? , datacadastro = ? " +
                "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;


        // Criar conexao com o banco
        try {
            conn = ConnectionFactory.createConnectionFirebird();
            //Criar a Classe para executar a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            //adicionar os valores para atualizar
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            //............ID do registro ...........\\
            pstm.setInt(4, contato.getId());

            //......Exercutar a query.....\\
            pstm.executeUpdate();
            System.out.println("Nome atualizado com sucesso!!!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstm != null) {
                    conn.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
        //..................................................Contatos..........................................................\\
        public List<Contato> getContatos () {

            String sql = "SELECT * FROM contatos";

            List<Contato> contatos = new ArrayList<Contato>();

            Connection conn = null;
            PreparedStatement pstm = null;
            //Classe que vai recuperar os dados do banco. ***SELECT****
            ResultSet rset = null;

            try {
                conn = ConnectionFactory.createConnectionFirebird();

                pstm = (PreparedStatement) conn.prepareStatement(sql);

                rset = pstm.executeQuery();

                while (rset.next()) {

                    Contato contato = new Contato();

                    //Recuperar o id
                    contato.setId(rset.getInt("id"));
                    //Recuperar o nome
                    contato.setNome(rset.getString("nome"));
                    //Recuperar a idade
                    contato.setId(rset.getInt("idade"));
                    //Recuperar a data de cadastrado
                    contato.setDataCadastro(rset.getDate("datacadastro"));

                    contatos.add(contato);

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rset != null) {
                        rset.close();
                    }

                    if (pstm != null) {
                        pstm.close();
                    }

                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return contatos;
        }

    }





