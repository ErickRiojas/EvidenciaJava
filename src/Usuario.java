public class Usuario {

    private String usuario;
    private String contraseña;

    public Usuario(String u, String c) {
        usuario = u;
        contraseña = c;
    }

    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Usuario) {
            Usuario credenciales = (Usuario)objeto;
            if (usuario.equals(credenciales.usuario) && contraseña.equals(credenciales.contraseña))
                return true;
            else
                return false;
        }
        else
            return false;
    }

}