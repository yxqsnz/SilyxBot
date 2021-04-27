# Silyx, Apenas outro simples bot para o Discord!

## 🚀 Executando
### Prerequisitos
* JDK versão 15 ou superior você pode baixar [clicando aqui](https://www.oracle.com/br/java/technologies/javase-downloads.html)
* [MongoDB](https://www.mongodb.com/)
### Compilando
`./gradlew build` 
## Uso
`java -jar caminho_do_jar_do_silyx`
*antes disso você precisa de um arquivo chamado `config.json`*
### exemplo do arquivo `config.json`
```json
{
  "environment": "nada",
  "discord": {
    "token": "TOKEN",
    "prefix": "Sx",
    "bot_owners": ["0000000"]
  },
  "database": {
    "mongo_uri": "URL_DO_MONGO_DB"
  }
}
```

# 🗒️ Licensa
 + Esse bot está usando a licensa GNU GPL v3.
# Autor
   🧔 Yxqsnz
   + Github: [yxqsnz](https://github.com/yxqsnz)
 # 🤝 Contribuindo
   + PRs e issues são bem vindos.
