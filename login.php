<?php
$host = "localhost";
$usuario = "root";
$contrasena = "root";
$bd = "hot_dog_mania_db";

$conexion = new mysqli($host, $usuario, $contrasena, $bd);

if ($conexion->connect_error) {
    die(json_encode(["error" => "Error de conexión: " . $conexion->connect_error]));
}

if (!isset($_POST["email"]) || !isset($_POST["contrasena"])) {
    die(json_encode(["error" => "Faltan datos"]));
}

$email = $_POST["email"];
$contrasena = $_POST["contrasena"];

$sql = "SELECT * FROM usuarios WHERE email = ?";
$stmt = $conexion->prepare($sql);
$stmt->bind_param("s", $email);
$stmt->execute();
$resultado = $stmt->get_result();

if ($resultado->num_rows > 0) {
    $usuario = $resultado->fetch_assoc();
    
    // Verificar contraseña
    if (password_verify($contrasena, $usuario["contrasena"])) {
        echo json_encode(["success" => "Login exitoso", "id_usuario" => $usuario["id_usuario"], "nombre" => $usuario["nombre"]]);
    } else {
        echo json_encode(["error" => "Contraseña incorrecta"]);
    }
} else {
    echo json_encode(["error" => "Usuario no encontrado"]);
}

$stmt->close();
$conexion->close();
?>
