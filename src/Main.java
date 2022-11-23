import com.sun.jdi.connect.AttachingConnector;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args){
        if (validarAcceso()) {
            System.out.println("Usuario autorizado");
            menu();
        } else {
            System.out.println("\nUsuario no autorizado.");
        }

        System.out.println("FIN DE PROGRAMA");
    }

    private static boolean validarAcceso() {
        Scanner leer = new Scanner(System.in);
        usuarios.add(new Usuario("Erick", "1234"));
        usuarios.add(new Usuario("Alfredo", "5678"));
        usuarios.add(new Usuario("Adrian", "2000"));
        System.out.println("CLINICA AGENDA CITA");
        System.out.print("Usuario: ");
        String usuario = leer.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = leer.nextLine();
        Usuario admin = new Usuario(usuario, contraseña);
        return usuarios.contains(admin);
    }

    private static void menu(){
        int opc;
        boolean salir = false;
        Scanner leer = new Scanner(System.in);
        System.out.println("Haz accedido al menú de la clínica");
        System.out.println("Que deseas realizar?");
        System.out.println("1. Accede menú de doctores");
        System.out.println("2. Accede menú de pacientes");
        System.out.println("3. Acccede menú de citas");
        System.out.println("4. Salir del programa");
        opc = leer.nextInt();

        switch (opc){
            case 1:
                Doctores.mainDoctor();
                break;
            case 2:
                Pacientes.mainPacientes();
                break;
            case 3:
                Agendar.mainAgendar();
                break;
            case 4:
                salir = true;
                System.out.println("Finalizando");
        }
    }
}
