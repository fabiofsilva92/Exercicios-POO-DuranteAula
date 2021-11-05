package Aula14;

import java.sql.*;

public class TesteConexao {

    private static final String DBURL = "jdbc:mariadb://localhost/petdb";
    private static final String DBUSER = "root";
    private static final String DBPASS = "";


    public static void main(String[] args) throws Exception {
        System.out.println("Teste de conexão em DB");
        Class.forName("org.mariadb.jdbc.Driver");
        System.out.println("Classe de conexão carregada");
        Connection con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        System.out.println("Conectado no BD");

        //Inserir pet
        String sql = "INSERT INTO pet (id, nome, raca, peso, nascimento) " +
                "VALUES (1, 'TOTO', 'VIRALATA', 10.50, '2017-11-09')";

        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.executeUpdate();

        System.out.println("Comando INSERT INTO executado");

        //Query para mostrar todos os pets
        String query = "SELECT * FROM pet";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            String id = rs.getString("id");
            String nome = rs.getString("nome");
            String raca = rs.getString("raca");
            String peso = rs.getString("peso");
            String nascimento = rs.getString("nascimento");

            System.out.format("%s, %s, %s, %s, %s\n" , id, nome, raca, peso, nascimento);
        }

        System.out.println("QUERY executada");
        con.close();
    }

}
