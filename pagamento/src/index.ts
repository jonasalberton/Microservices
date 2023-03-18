import { consume, sendToQueue } from './lib/queue';
import { INICIA_PAGAMENTO, BAIXA_ESTOQUE, PAGAMENTO_CONFIRMADO} from '../../filas'

function onPagamentoStarted({content}: any) {
  console.log('data', JSON.parse(content));
  sendToQueue(BAIXA_ESTOQUE, { name: 'abc'})
}

function listemToQueues() {
  consume(INICIA_PAGAMENTO, onPagamentoStarted)
}

function run() {
  console.log('Init Pagamento Service');
  listemToQueues();
}

run()