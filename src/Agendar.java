import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Agendar {
    HashMap<String,String> listaCitas =new HashMap<>();
    File file = new File("C:\\Users\\Erick Mtz\\Documents\\BaseDatos\\Citas.txt");

    public void cargar(){
        try{
            if(file.exists()){
                BufferedReader loadedFile = new BufferedReader(new FileReader(file));
                String cargar;
                while((cargar = loadedFile.readLine())!=null){
                    String[]cita = cargar.split(",");
                    listaCitas.put(cita[0], cita[1]);
                }
                loadedFile.close();
            }
        } catch (Exception e){
            System.out.println("Problemas con el metodo load(): " + e.getMessage());
        }
    }
    public void guardar(){
        String fecha;
        String paciente;
        String doctor;
        String citas="";
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            if(!listaCitas.entrySet().isEmpty()){
                for(Map.Entry<String,String> entry:listaCitas.entrySet()){
                    fecha = entry.getKey();
                    paciente = entry.getValue();
                    doctor = entry.getValue();
                    citas+= "Fecha de la cita : "+fecha+" Nombre del paciente: "+paciente+"Nombre del doctor: "+doctor;
                }
            }
            BufferedWriter fileToSave = new BufferedWriter(
                    new OutputStreamWriter
                            (new FileOutputStream(file, false)));
            fileToSave.write(citas);
            fileToSave.close();

        } catch(Exception e){
            System.out.println("Problemas con el metodo save(): " + e.getMessage());
        }
    }
    public void lista(){
        if(listaCitas.entrySet().isEmpty()){
            System.out.println("No hay citas agendadas");
        } else {
            System.out.println("Citas: ");
            for(Map.Entry<String,String> entry:listaCitas.entrySet()){
                System.out.printf("%s%s%s", entry.getKey(),
                        ": ",entry.getValue() +"\n");
            }
        }
    }
    public void crear(String fecha, String paciente, String doctor){
        if (!listaCitas.containsKey(paciente)){
            listaCitas.put(fecha, paciente);
            System.out.printf(" La cita para la fecha "+ fecha+" fue agregada exitosamente");
        } else {
            System.out.println("Esta cita ya existe");
        }
    }
    public static void mainAgendar(){

        Scanner leer = new Scanner(System.in);
        boolean res = false;
        Agendar agendar = new Agendar();
        agendar.cargar();
        while (!res){
            System.out.println("\nBienvenido a la agenda de citas, selecciona una opción:");
            System.out.println("1. Mostrar tu lista de citas");
            System.out.println("2. Añadir una cita nueva");
            System.out.println("3. Guardar citas y salir");
            switch(leer.nextInt()){
                case 1:
                    agendar.lista();
                    break;
                case 2:
                    System.out.println("Introduce la fecha de la cita: ");
                    String fecha = leer.next();
                    System.out.println("Introduce el nombre del paciente: ");
                    String paciente = leer.next();
                    System.out.println("Introduce el nombre del doctor: ");
                    String doctor = leer.next();
                    agendar.crear(fecha, paciente, doctor);
                    break;
                case 3:
                    System.out.println("Guardando doctores y saliendo");
                    agendar.guardar();
                    res = true;
                    break;
                default:
                    System.out.println("Por favor, escribe una opción válida");
                    break;
            }
        }
    }
}
