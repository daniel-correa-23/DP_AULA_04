# DP_AULA_04
Exemplos Android Nativo - Trabalhando com SharedPreferences

Preferências Compartilhadas é a primeira opção disponível para armazenamento de dados no Android, através das quais podemos armazenar 
dados primitivos em uma estrutura key-value (chave-valor).

Para entendermos como uma estrutura key-value funciona, vamos analisar o exemplo a seguir:
{
  “perfil” : “administrador”,
  “valor” : 75.4,
  “quantidade” : 28,
  “privilegio” : true
}
Temos quatro registros com chaves do tipo String e valores primitivos associados a cada chave.
Neste exemplo, as chaves são “perfil”, “valor”, “quantidade” e “privilegio”. 

Atenção, as chaves obrigatoriamente devem ser do tipo String.
