# File Upload Project

This project is a backend application for handling file uploads and download. It provides endpoints for uploading files to the server and serving uploaded files.

## Features

- Upload images to the server.
- Automatically generate unique filenames for uploaded images if a file with the same name already exists.
- Serve uploaded files to clients.

## Tech Stack

- Java
- Spring Boot
- Maven

## Getting Started

1. Clone the repository: `git clone https://github.com/yourusername/File-Upload.git`
2. Navigate to the project directory: `cd File-Upload`
3. Build the project: `mvn clean install`
4. Run the application: `java -jar target/FileUploadApplication.jar`

## Usage

### Uploading a File

To upload a file, send a POST request to the `/file/upload` endpoint with the file attached as a form-data parameter named `image`.

Example using cURL:

```bash
curl -X POST -F "image=@/path/to/your/image.jpg" http://localhost:8080/file/upload
```
### Project Flow
```bash
                    +-----------------+
                    |   Client        |
                    +--------+--------+
                             |
                             v
                    +-----------------+
                    |   Backend API   |
                    +--------+--------+
                             |
             +---------------|---------------+
             |               v               |
             |        File Upload Service    |
             |          (Spring Boot)        |
             |               |               |
             +---------------|---------------+
                             |
                             v
                    +-----------------+
                    |   File Storage   |
                    |   (Local Disk)   |
                    +-----------------+
```
## Endpoints

### Upload File

- **URL:** `/file/upload`
- **Method:** POST
- **Request Parameters:**
    - `image`: The file to be uploaded.
- **Response:**
    - Successful Response:
        - **Status Code:** 200 OK
        - **Content:**
          ```json
          {
              "fileName": "example.jpg",
              "message": "Image is successfully Uploaded"
          }
          ```
    - Error Response:
        - **Status Code:** 500 Internal Server Error
        - **Content:**
          ```json
          {
              "fileName": "example.jpg",
              "message": "File was not uploaded due to some error"
          }
          ```

### Download File

- **URL:** `/file/download/{imageName}`
- **Method:** GET
- **Request Parameters:**
    - `imageName`: The name of the image file to download.
- **Response:**
    - The image file to be downloaded.


