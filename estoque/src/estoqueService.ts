import { consume } from "./lib/queue";
import { BAIXA_ESTOQUE } from '../../filas'
import { prisma } from "./lib/prisma";

interface BaixaEstoque {
  idProduto: string,
  quantidade: number
}

async function onBaixaEstoque({content}: any) {
  const baixa: BaixaEstoque = JSON.parse(content);

  const produto = await prisma.produto.findUnique({ where: { id: baixa.idProduto }})

  if (produto) {
    await prisma.produto.update({ where: { id: baixa.idProduto }, data: {
      quantidade: produto.quantidade - baixa.quantidade
    }})
  }
}

export function listemToQueues() {
  consume(BAIXA_ESTOQUE, onBaixaEstoque)
}


