import { consume } from "./lib/queue";
import { BAIXA_ESTOQUE } from '../../filas'
import { prisma } from "./lib/prisma";

async function onBaixaEstoque({content}: any) {
  console.log('data', JSON.parse(content));
  const id = '2198403d-dcb1-4aad-a2a1-898d5a21f1f6'
  const produto = await prisma.produto.findUnique({ where: { id }})

  if (produto) {
    await prisma.produto.update({ where: { id }, data: {
      quantidade: produto.quantidade - 1
    }})
  }
}

export function listemToQueues() {
  consume(BAIXA_ESTOQUE, onBaixaEstoque)
}


