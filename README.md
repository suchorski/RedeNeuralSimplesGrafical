# RedeNeuralSimplesGrafical
Classe que implementa gráficos para testes com a biblioteca [RedeNeuralSimples](https://github.com/suchorski/RedeNeuralSimples)

## Utilização

Basta simplesmente instanciar a classe utilizando uma Rede Neural e utilizar o método **atualizar** para atualizar a rede.

```
RedeNeural redeNeural = ...;
RedeNeuralGrafical redeNeuralGrafical = new RedeNeuralGrafical(redeNeural);
redeNeuralGrafical.atualizar();
```

## Opcionais
Opcionalmente pode-se utilizar os métodos **setRedeNeural** para trocar a Rede Neural e/ou **setTextos** para definir textos a serem mostrados na tela do gráfico.

### Sugestões de melhorias ou correção de bugs basta entrar em contato.