import { consume, sendToQueue } from './lib/queue';
import { INICIA_PAGAMENTO, BAIXA_ESTOQUE, PAGAMENTO_STATUS} from '../../filas'

interface PagamentoInit {
  idPedido: number,
  idPessoa: number,
  idProduto: string,
  quantidade: number
  tipoPagamento: 'pix' | 'cartao'
}

interface PagamentoStatus {
  idPedido: number,
  status: 'APROVADO' | 'NEGADO'
}

interface BaixaEstoque {
  idProduto: string,
  quantidade: number
}

function sendPagamentoStatus(pa: PagamentoStatus) {
  sendToQueue(PAGAMENTO_STATUS, pa);
}

function sendBaixaEstoque(ba: BaixaEstoque) {
  sendToQueue(BAIXA_ESTOQUE, ba);
}

function onPagamentoStarted({content}: any) {
  const pagamento: PagamentoInit = JSON.parse(content);

  if (pagamento.tipoPagamento === 'pix') {
    sendBaixaEstoque({idProduto: pagamento.idProduto, quantidade: pagamento.quantidade})
    sendPagamentoStatus({ idPedido: pagamento.idPedido, status: 'APROVADO'})
    return;
  } 

  sendPagamentoStatus({ idPedido: pagamento.idPedido, status: 'NEGADO'})
}

function listemToQueues() {
  consume(INICIA_PAGAMENTO, onPagamentoStarted)
}

function run() {
  console.log('Init Pagamento Service');
  listemToQueues();
}

run()