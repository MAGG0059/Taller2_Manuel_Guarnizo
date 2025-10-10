FROM node:18

WORKDIR /app

# Copiar solo los archivos de Node.js necesarios
COPY package.json package-lock.json ./
RUN npm install

# Copiar el resto de los archivos (excluyendo los de Java si es necesario)
COPY src/ ./src/
COPY server.js ./

EXPOSE 3000
CMD ["npm", "start"]