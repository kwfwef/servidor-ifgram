/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifgram.servidor;

import com.sun.corba.se.spi.activation.Server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author gabriel
 */
public class IFgramServidor {

   private  ServerSocket serverSocket;
   
   private void  criarServerSocket(int porta) throws IOException{
   
   serverSocket = new ServerSocket(porta);}
   
   private Socket esperarConexao() throws IOException{
   
   Socket socket = serverSocket.accept();
   return socket;}
   
   private void fecharSocket(Socket s) throws IOException{
       s.close();
   }
   
   private void tratarConexao(Socket socket) throws IOException{
   //protocolo da aplicacao
   try{
   ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
   ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
   
   //protocolo - saber se tem que enviar ou receber uma mensagem
   
   String msg = input.readUTF();
       System.out.println("Mensgem recebida");
       output.writeUTF("Hello world");
       
       // fechar streams de entrada e saida
       input.close();
       output.close();
       
           } catch(IOException e){
           // tratamento de falhas}
           } finally{
   // final do tratamento do protocolo : encerrar ele de vez - fechar socket entre servidor e cliente
       fecharSocket(socket);
   }          
           }
   
    public static void main(String[] args) {
            try{
        IFgramServidor server = new IFgramServidor(){};
                System.out.println("aguardando conexao...");
        server.criarServerSocket(5555);
        Socket socket = server.esperarConexao(); //protocolo
                System.out.println("cliente conectado");
        server.tratarConexao(socket);
                System.out.println("cliente finalizado");
    }catch(IOException e){}
        
    }
    
    
}
