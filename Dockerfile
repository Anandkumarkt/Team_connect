# Use the official Ubuntu image as the base
FROM ubuntu:latest

# Set the working directory inside the container
WORKDIR /app

# Copy the script into the container at /app
COPY hello.sh .

# Give execute permissions to the script
RUN chmod +x hello.sh

RUN find . -name hello.sh

RUN /bin/bash -c ./hello.sh


# Run the script when the container starts
CMD ["./hello.sh"]
