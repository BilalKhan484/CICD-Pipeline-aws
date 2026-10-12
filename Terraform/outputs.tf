output "app_public_url" {
  value       = "http://${aws_instance.app_server.public_ip}"
  description = "Public URL of the Spring Boot application"
}