provider "aws" {
  region = var.aws_region
}

# Security Group to allow HTTP traffic and SSH
resource "aws_security_group" "app_sg" {
  name        = "spring-app-sg"
  description = "Allow inbound traffic for Spring Boot and SSH"

  ingress {
    description = "HTTP Web Traffic"
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    description = "SSH Access"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# Fetch latest Ubuntu 24.04 LTS AMI
data "aws_ami" "ubuntu" {
  most_recent = true
  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-jammy-22.04-amd64-server-*"]
  }
  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
  owners = ["099720109477"] # Canonical
}

# Provision EC2 Instance
resource "aws_instance" "app_server" {
  ami                  = data.aws_ami.ubuntu.id
  instance_type        = "t3.micro"
  security_groups      = [aws_security_group.app_sg.name]
  user_data            = templatefile("${path.module}/user-data.sh", { docker_image = var.docker_image })

  tags = {
    Name = "Spring-Boot-DevOps-Instance"
  }
}