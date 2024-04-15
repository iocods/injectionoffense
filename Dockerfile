FROM sbtscala/scala-sbt:eclipse-temurin-jammy-21.0.2_13_1.9.9_3.4.0

# Install unzip utility
RUN apt-get update && apt-get install -y unzip

# Set the working directory in the container
WORKDIR /app

# Copy the Play application ZIP into the container
COPY target/universal/scalacommandinjectiondefence-1.0-SNAPSHOT.zip /app/

COPY ./prod.conf .
# Unzip the Play application
RUN unzip scalacommandinjectiondefence-1.0-SNAPSHOT.zip && \
    rm scalacommandinjectiondefence-1.0-SNAPSHOT.zip

# Set the default environment variables for Play
ENV PATH="/app/scalacommandinjectiondefence-1.0-SNAPSHOT/bin:${PATH}"
ENV PLAY_SECRET="Xk#2Lpio18?2sUYnYq3t6v9y/B?E(H+MbPeShVkYp3s6v9y$B&E)H@Mck3wjWnZr4u7x"

# Expose the port that the Play application will run on
EXPOSE 9000

# Set the default command to run your Play application
CMD ["scalacommandinjectiondefence-1.0-SNAPSHOT/bin/scalacommandinjectiondefence", "-Dplay.http.secret.key='QCY?tAnfk?aZ?iwrNwnxIlR6CTf:G3gf:90Latabg@5241AB`R5W:1uDFN];Ik@n'"]