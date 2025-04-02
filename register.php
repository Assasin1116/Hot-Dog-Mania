<?php
header("Content-Type: application/json");

$host = "localhost";
$usuario = "root";
$contrasena = "root";
$bd = "hot_dog_mania_db";

$conexion = new mysqli($host, $usuario, $contrasena, $bd);

if ($conexion->connect_error) {
    die(json_encode(["error" => "Error de conexión: " . $conexion->connect_error]));
}

// Verificar que se enviaron los datos
if (!isset($_POST["nombre"], $_POST["email"], $_POST["contrasena"])) {
    die(json_encode(["error" => "Faltan datos"]));
}

$nombre = $_POST["nombre"];
$email = $_POST["email"];
$contrasena = password_hash($_POST["contrasena"], PASSWORD_BCRYPT); // Encriptar contraseña

// Verificar si el usuario ya existe
$verificarUsuario = $conexion->prepare("SELECT id_usuario FROM usuarios WHERE email = ?");
$verificarUsuario->bind_param("s", $email);
$verificarUsuario->execute();
$verificarUsuario->store_result();

if ($verificarUsuario->num_rows > 0) {
    die(json_encode(["error" => "El email ya está registrado"]));
}

// Insertar usuario
$sql = $conexion->prepare("INSERT INTO usuarios (nombre, email, contrasena) VALUES (?, ?, ?)");
$sql->bind_param("sss", $nombre, $email, $contrasena);

if ($sql->execute()) {
    echo json_encode(["success" => "Usuario registrado correctamente"]);
} else {
    echo json_encode(["error" => "Error al registrar usuario: " . $conexion->error]);
}

$conexion->close();
?>
