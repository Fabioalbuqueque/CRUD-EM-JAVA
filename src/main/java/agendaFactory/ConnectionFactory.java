package agendaFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    //Nome do mysql
    private static final String USERNAME = "SYSDBA";

    //Senha do banco
    private static final String PASSWORD = "masterkey";

    //Caminho do banco de dados.
    private static final String DATABASE_URL = "jdbc:firebirdsql://localhost:3050/D:/agenda/AGENDA.FDB";

    /*
     * Conexão com o banco de dados
     */
    public static Connection createConnectionFirebird() throws Exception {
        //Faz com que a classe seja carregada pela JVM
        try{
            Class.forName("org.firebirdsql.jdbc.FBDriver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        //Cria a conexão com o banco de dados
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;
    }


    public static void main(String[] args) throws Exception {

        try {
            //Recuperar conexão com o banco
            Connection con = createConnectionFirebird();

            //Testar se a conexão é nula
            if(con!=null) {
                System.out.println("Conexão com sucesso!");
                con.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

