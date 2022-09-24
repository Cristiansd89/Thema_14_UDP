package Ejemplo_03_Array_Fusion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteArrays {

	public static void main(String[] args) {
		
		try {
			//Me creo un socket para conectar con el servidor
			Socket cliente = new Socket ("localhost", 8800);
			
			//Inicializamos 2 arrays
			int [] array1 = {3,6,8,1,2,7,9};
			int [] array2 = {1,2,5,7,9};
			
			//Ordenamos los arrays
			ordenacionBurbuja(array1);
			ordenacionBurbuja(array2);
			
			//Eviamos a pantalla los arrays ordenados
			
			visualizarArray(array1);
			System.out.println();
			visualizarArray(array2);
			System.out.println();
			//Enviamos los arrays al servidor
			
			ObjectOutputStream obj1 = new ObjectOutputStream(cliente.getOutputStream());
			obj1.writeObject(array1);
			obj1.writeObject(array2);
			
			//Leemos el array fusionado que llega del servidor
			
			ObjectInputStream obj2 = new ObjectInputStream(cliente.getInputStream());
			try {
				int [] array3 = (int[]) obj2.readObject();
				
				//Visualizamos el array fusionado
				System.out.println("El array fusionado");
				visualizarArray(array3);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			cliente.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void ordenacionBurbuja(int[]array)
	{
		boolean hayIntercambio;
		do
		{
			hayIntercambio = false;
			for(int i = 0; i < array.length-1; i++)
			{
				
				if(array[i] > (array[i+1]))
				{
					//Intercambiamos los elementos
					int aux = array[i+1];
					array[i+1] = array[i];
					array[i] = aux;
					hayIntercambio = true;
				}
			}
		}while(hayIntercambio);
	}
	
	public static void visualizarArray(int[] array)
	{
			for(int i = 0; i < array.length; i++)
			{
				System.out.print(array[i] + " ");	
			}
		}
	}

