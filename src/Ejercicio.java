import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.Duration;

class Ejercicio {
    // Máximo de usuarios permitidos
    static final int MAX_USERS = 5;
    // Array de usuarios
    static Usuario[] usuarios = new Usuario[MAX_USERS];
    static int contUsuarios = 0; // Para saber cuántos usuarios se han registrado

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nBienvenido");
            System.out.println("1. Registrar Vehiculo");
            System.out.println("2. Iniciar Sección");
            System.out.println("3. Salir y entregar factura");
            System.out.println("4. Salir del programa");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt(); // Aquí pedimos al usuario que ingrese una opción

            switch (opcion) {
                case 1:
                    registrarVehiculo();
                    break;
                case 2:
                    iniciarSeccion();
                    break;
                case 3:
                    salirYFacturar();
                    break;
                case 4:
                    System.out.println("Saliendo del parqueadero...Buen viaje ;)" );
                    break;
                default:
                    System.out.println("Ingrese una opción válida!");
            }

        } while (opcion != 4);
        sc.close();
    }

    // Método para registrar un vehículo
    public static void registrarVehiculo() {
        if (contUsuarios >= MAX_USERS) {
            System.out.println("El parqueadero está lleno :(");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su nombre: ");
        String nombreUsuario = sc.nextLine();

        System.out.print("Ingresa la placa de tu vehículo: ");
        String placaVehiculo = sc.nextLine();

        System.out.print("Ingresa el modelo de tu vehículo: ");
        String modeloVehiculo = sc.nextLine();

        System.out.print("Establece tu contraseña: ");
        String contraseñaUsuario = sc.nextLine();

        // Guardar la hora de ingreso
        LocalDateTime horaIngreso = LocalDateTime.now();

        // Crear nuevo usuario y añadir al array
        Usuario nuevoUsuario = new Usuario(nombreUsuario, placaVehiculo, modeloVehiculo, contraseñaUsuario, horaIngreso);
        usuarios[contUsuarios] = nuevoUsuario;
        contUsuarios++;

        System.out.println("Tu vehículo ha sido registrado exitosamente a las: " + horaIngreso);
    }

    // Método para iniciar sesión
    public static void iniciarSeccion() {
        if (contUsuarios == 0) {
            System.out.println("No hay vehículos registrados.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = sc.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contraseña = sc.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < contUsuarios; i++) {
            if (usuarios[i].getNombreUsuario().equals(nombreUsuario) &&
                    usuarios[i].getContraseña().equals(contraseña)) {
                System.out.println("Inicio de sesión exitoso. Bienvenido al parqueadero, " + nombreUsuario + "!");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Nombre de usuario no encontrado o contraseña incorrecta.");
        }
    }

    // Método para registrar la salida y generar la factura
    public static void salirYFacturar() {
        if (contUsuarios == 0) {
            System.out.println("No hay vehículos registrados.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese su nombre de usuario para salir: ");
        String nombreUsuario = sc.nextLine();

        System.out.print("Ingrese la contraseña: ");
        String contraseña = sc.nextLine();

        boolean encontrado = false;
        for (int i = 0; i < contUsuarios; i++) {
            if (usuarios[i].getNombreUsuario().equals(nombreUsuario) &&
                    usuarios[i].getContraseña().equals(contraseña)) {
                // Calcular tiempo de estacionamiento
                LocalDateTime horaSalida = LocalDateTime.now();
                Duration duracion = Duration.between(usuarios[i].getHoraIngreso(), horaSalida);
                long minutosEstacionado = duracion.toMinutes();

                // Calcular costo
                long costo = minutosEstacionado * 3000;

                // Imprimir factura
                System.out.println("Has estado estacionado por: " + minutosEstacionado + " minutos.");
                System.out.println("El costo total es: " + costo + " pesos.");
                System.out.println("Hora de salida: " + horaSalida);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Nombre de usuario no encontrado o contraseña incorrecta.");
        }
    }
}

// Clase Usuario
class Usuario {
    private String nombreUsuario;
    private String placaVehiculo;
    private String modeloVehiculo;
    private String contraseña;
    private LocalDateTime horaIngreso;

    public Usuario(String nombreUsuario, String placaVehiculo, String modeloVehiculo, String contraseña, LocalDateTime horaIngreso) {
        this.nombreUsuario = nombreUsuario;
        this.placaVehiculo = placaVehiculo;
        this.modeloVehiculo = modeloVehiculo;
        this.contraseña = contraseña;
        this.horaIngreso = horaIngreso;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }
}
