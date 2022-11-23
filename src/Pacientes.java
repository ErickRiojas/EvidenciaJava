import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Pacientes {
    HashMap<String,String> listaPacientes =new HashMap<>();
    File file = new File("C:\\Users\\Erick Mtz\\Documents\\BaseDatos\\Pacientes.txt");

    public void cargar(){
        try{
            if(file.exists()){
                BufferedReader loadedFile = new BufferedReader(new FileReader(file));
                String cargar;
                while((cargar = loadedFile.readLine())!=null){
                    String[]paciente = cargar.split(",");
                    listaPacientes.put(paciente[0], paciente[1]);
                }
                loadedFile.close();
            }
        } catch (Exception e){
            System.out.println("Problemas con el metodo load(): " + e.getMessage());
        }
    }
    public void guardar(){
        String nombre;
        String edad;
        String pacientes="";
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            if(!listaPacientes.entrySet().isEmpty()){
                for(Map.Entry<String,String> entry:listaPacientes.entrySet()){
                    edad = entry.getKey();
                    nombre = entry.getValue();
                    pacientes+= "Nombre del paciente : "+nombre+" Edad: "+edad+"\r\n";
                }
            }
            BufferedWriter fileToSave = new BufferedWriter(
                    new OutputStreamWriter
                            (new FileOutputStream(file, false)));
            fileToSave.write(pacientes);
            fileToSave.close();

        } catch(Exception e){
            System.out.println("Problemas con el metodo save(): " + e.getMessage());
        }
    }
    public void lista(){
        if(listaPacientes.entrySet().isEmpty()){
            System.out.println("No tienes pacientes agregados");
        } else {
            System.out.println("Pacientes: ");
            for(Map.Entry<String,String> entry:listaPacientes.entrySet()){
                System.out.printf("%s%s%s", entry.getKey(),
                        ": ",entry.getValue() +"\n");
            }
        }
    }
    public void crear(String nombre, String edad){
        if (!listaPacientes.containsKey(nombre)){
            listaPacientes.put(nombre, edad);
            System.out.printf(" El paciente "+ nombre+" fue agregado exitosamente");
        } else {
            System.out.println("Este paciente ya existe");
        }
    }
    public static void mainPacientes(){

        Scanner leer = new Scanner(System.in);
        boolean res = false;
        Pacientes pacientes = new Pacientes();
        pacientes.cargar();
        while (!res){
            System.out.println("\nBienvenido a la agenda de pacientes, selecciona una opción:");
            System.out.println("1. Mostrar tu lista de pacientes");
            System.out.println("2. Añadir un paciente nuevo");
            System.out.println("3. Guardar pacientes y salir");
            switch(leer.nextInt()){
                case 1:
                    pacientes.lista();
                    break;
                case 2:
                    System.out.println("Introduce el nombre: ");
                    String nombre = leer.next();
                    System.out.println("Introduce la edad: ");
                    String edad = leer.next();
                    pacientes.crear(nombre, edad);
                    break;
                case 3:
                    System.out.println("Guardando doctores y saliendo");
                    pacientes.guardar();
                    res = true;
                    break;
                default:
                    System.out.println("Por favor, escribe una opción válida");
                    break;
            }
        }
    }
}
