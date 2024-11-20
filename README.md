# Taller4

**Taller4** es una aplicación de Android que permite a los usuarios gestionar una colección de usuarios. Los usuarios pueden ser añadidos, visualizados y eliminados de una base de datos Firebase Firestore. La aplicación está construida utilizando **Jetpack Compose** para la interfaz de usuario y **Firebase** para el backend.

## **Link al repositorio**
https://github.com/dxn1l/Taller4

## **Características**

- **Añadir un nuevo usuario** con un ID personalizado.
- **Ver una lista de usuarios.**
- **Eliminar un usuario** por ID.
- **Ver detalles** del usuario.
- Pantalla de **configuración** para cambiar el color de fondo según la orientación del dispositivo.

## **Pantallas**

### 1. **Pantalla de Bienvenida**
- Pantalla inicial con un mensaje de saludo y un botón para ver usuarios.

### 2. **Pantalla de Añadir Usuario**
- Permite añadir un nuevo usuario con campos para:
    - ID
    - Nombre
    - Apellido
    - Dirección
    - Teléfono
    - Email
    - Edad
    - Género

### 3. **Pantalla de Ver Usuarios**
- Muestra la lista de usuarios.
- Permite eliminar un usuario.
- Permite ver detalles de un usuario.

### 4. **Pantalla de Detalles del Usuario**
- Muestra información detallada sobre un usuario seleccionado.

### 5. **Pantalla de Configuración**
- Cambia el color de fondo según la orientación del dispositivo:
    - Negro para modo horizontal.
    - Blanco para modo vertical.
- Botón de reinicio para volver al color de fondo blanco.

## **Uso**

### 1. **Añadir un Usuario**
- Navega a la pantalla **"Añadir Usuario"**.
- Rellena los detalles del usuario, incluyendo un ID único.
- Haz clic en el botón **"Añadir"** para guardar el usuario en Firebase Firestore.

### 2. **Ver Usuarios**
- Navega a la pantalla **"Ver Usuarios"**.
- Visualiza la lista de usuarios.
- Haz clic en un usuario para:
    - Ver detalles.
    - Eliminar el usuario.

### 3. **Eliminar un Usuario**
- En la pantalla **"Ver Usuarios"**, haz clic en el icono de **eliminar** junto al usuario que deseas eliminar.
- Confirma la eliminación en el diálogo que aparece.

### 4. **Configuración**
- Navega a la pantalla **"Configuración"**.
- Gira el dispositivo para cambiar el color de fondo a **negro**.
- Usa el botón **Reiniciar** para cambiar el color de fondo a **blanco**.

## **Estructura del Proyecto**

- **`MainActivity.kt`**: Punto de entrada de la aplicación.
- **`Navigation/TallerApp.kt`**: Maneja la navegación entre diferentes pantallas.
- **`Database/FirebaseDataBaseRepository.kt`**: Contiene funciones para interactuar con Firebase Firestore.
- **`Database/User.kt`**: Clase de datos que representa un usuario.
- **`Screens/`**: Contiene todas las funciones composables para diferentes pantallas.
- **`widget/`**: Contiene clases para el widget de la aplicación.

## **Dependencias**

- **Jetpack Compose**
- **Firebase Firestore**
- **Bibliotecas de AndroidX**

