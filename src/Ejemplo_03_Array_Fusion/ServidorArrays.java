package Ejemplo_03_Array_Fusion;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorArrays
{  
   public static void main(String[] args )
   {  
      try
      {  
         int i = 1;
         ServerSocket s = new ServerSocket(8800);

         //bucle infinito
         while(true)
         {
            System.out.println("En espera de que se conecte un cliente\n");
            Socket comunicaCliente = s.accept( );
            System.out.println("atendiendo la peticion " + i);
            Thread tarea = new ManejadorEcoHilo(comunicaCliente, i);
            tarea.start();
            i++;
         }
      }
      catch (Exception e)
      {
         System.out.println(e.getMessage());       
         
      }
   }
}

/**
   Esta clase maneja el  cliente que conecta con el server socket
   
*/
class ManejadorEcoHilo extends Thread
{ 
   /**
      Construye un manejador.
      i es el socket conectado
      c es el contador
   */

   private Socket comunicaCliente;
   private int contador;


   public ManejadorEcoHilo(Socket i, int c)
   { 
      comunicaCliente = i;
      contador = c;
   }
   
   public static int[] fusion (int array1[],int [] array2)
	{
		//Creamos espacio para array3
		int array3[] = new int[array1.length + array2.length];
		//Nos situamos en la primera posición de los 3 arrays
		int i = 0;
		int j = 0;
		int k = 0;
		/*Recorremos ambos arrays mientras tengan elementos, comparamos los elementos
		 * y el elemento más pequeño lo almacenamos en el tercer array
		 */
		while(i < array1.length && j < array2.length) 
		{
			if(array1[i] < array2[j])
			{
				array3[k] = array1[i];
				i++;
				k++;
			}
			else
			{
				array3[k] = array2[j];
				j++;
				k++;
			}	
		}
		//Almacenamos en el tercer array los elemenos restantes
		while(i < array1.length)
		{
			array3[k] = array1[i];
			i++;
			k++;
		}
		while(j < array2.length)
		{
			array3[k] = array2[j];
			j++;
			k++;
		}
		return array3;
	}

   public void run()
   {  
      try
      {  
         // Recibimos los arrays del cliente
    	  ObjectInputStream obj1 = new ObjectInputStream(comunicaCliente.getInputStream());
    	  int [] array1 = (int[]) obj1.readObject();
    	  int [] array2 = (int[]) obj1.readObject();
    	  //Fusionamos los arrays
    	  int [] array3 = fusion(array1, array2);
    	  
    	  // Enviamos el array fusionado al cliente
    	  
    	  ObjectOutputStream obj2 = new ObjectOutputStream(comunicaCliente.getOutputStream());
    	  obj2.writeObject(array3);
    	  
    	  
         comunicaCliente.close();
      }
      catch (Exception e)
      {  
         System.out.println(e.getMessage());
      }
   }
}