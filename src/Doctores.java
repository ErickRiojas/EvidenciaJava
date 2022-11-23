import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Doctores {
   public static HashMap<String,String> listaDoctores =new HashMap<>();
    File file = new File("C:\\Users\\Erick Mtz\\Documents\\BaseDatos\\Doctores.txt");

    public void cargar(){
        try{
            if(file.exists()){
                BufferedReader loadedFile = new BufferedReader(new FileReader(file));
                String cargar;
                while((cargar = loadedFile.readLine())!=null){
                    String[]doctor = cargar.split(",");
                    listaDoctores.put(doctor[0], doctor[1]);
                }
                loadedFile.close();
            }
        } catch (Exception e){
            System.out.println("Problemas con el metodo load(): " + e.getMessage());
        }
    }
    public void guardar(){
        String nombre;
        String especialidad;
        String doctores="";
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            if(!listaDoctores.entrySet().isEmpty()){
                for(Map.Entry<String,String> entry:listaDoctores.entrySet()){
                    especialidad = entry.getKey();
                    nombre = entry.getValue();
                    doctores+= "Doctor: "+nombre+" Especialidad: "+especialidad+"\r\n";
                }
            }
            BufferedWriter fileToSave = new BufferedWriter(
                    new OutputStreamWriter
                            (new FileOutputStream(file, false)));
            fileToSave.write(doctores);
            fileToSave.close();

        } catch(Exception e){
            System.out.println("Problemas con el metodo save(): " + e.getMessage());
        }
    }
    public void lista(){
        if(listaDoctores.entrySet().isEmpty()){
            System.out.println("No tienes doctores agregados");
        } else {
            System.out.println("Doctores: ");
            for(Map.Entry<String,String> entry:listaDoctores.entrySet()){
                System.out.printf("%s%s%s", entry.getKey(),
                        ": ",entry.getValue() +"\n");
            }
        }
    }
    public void crear(String nombre, String especialidad){
        if (!listaDoctores.containsKey(nombre)){
            listaDoctores.put(nombre, especialidad);
            System.out.printf(" El doctor "+nombre+" fue agregado exitosamente");
        } else {
            System.out.println("Este doctor ya existe");
        }
    }

    public static void mainDoctor(){

        Scanner leer = new Scanner(System.in);
        boolean res = false;
        Doctores doctores = new Doctores();
        doctores.cargar();
        while (!res){
            System.out.println("\nBienvenido a la agenda de doctores, selecciona una opción:");
            System.out.println("1. Mostrar tu lista de doctores");
            System.out.println("2. Añadir un doctor nuevo");
            System.out.println("3. Guardar doctores y salir");
            switch(leer.nextInt()){
                case 1:
                    doctores.lista();
                    break;
                case 2:
                    System.out.println("Introduce el nombre: ");
                    String nombre = leer.next();
                    System.out.println("Introduce la especialidad: ");
                    String especialiddad = leer.next();
                    doctores.crear(nombre, especialiddad);
                    break;
                case 3:
                    System.out.println("Guardando doctores y saliendo");
                    doctores.guardar();
                    res = true;
                    break;
                default:
                    System.out.println("Por favor, escribe una opción válida");
                    break;
            }
        }
    }
}
