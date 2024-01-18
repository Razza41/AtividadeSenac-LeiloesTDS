/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {

   
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        
        try {
            conectaDAO bd = new conectaDAO();
            bd.connectDB();
            
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        prep = bd.getConexao().prepareStatement(sql);
        
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        
        prep.execute();
        
        JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel fazer o cadastro no banco de dados");
        }
   
        
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try {
                conectaDAO bd = new conectaDAO();
                bd.connectDB();
                
                String sql = "SELECT * FROM produtos";
                prep = bd.getConexao().prepareStatement(sql);
                ResultSet resposta = prep.executeQuery();
                
                ArrayList<ProdutosDTO> produtos = new ArrayList<ProdutosDTO>();
                
                while(resposta.next()){
                    ProdutosDTO produto = new ProdutosDTO();
                    produto.setId(resposta.getInt("id"));
                    produto.setNome(resposta.getString("Nome"));
                    produto.setValor(resposta.getInt("Valor"));
                    produto.setStatus(resposta.getString("Status"));
                    listagem.add(produto);
                }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao mostrar lista do banco de dados.");
        }
        
        return listagem;
    }
    
    
    
        
}

