import amqplib from 'amqplib'

function connect(){
  return amqplib.connect("amqp://guest:guest@localhost:5672")
                           .then(conn => conn.createChannel());
}

function createQueue(channel: amqplib.Channel , queue: string){
  return new Promise((resolve, reject) => {
    try{
      channel.assertQueue(queue, { durable: false });
      resolve(channel);
    }
    catch(err){ reject(err) }
  });
}

export function sendToQueue(queue: string, message: any){
  connect()
    .then(channel => createQueue(channel, queue))
    .then(channel => (channel as amqplib.Channel).sendToQueue(queue, Buffer.from(JSON.stringify(message))))
    .catch(err => console.log(err))
}
export function consume(queue: string, callback: () => void){
  connect()
    .then(channel => createQueue(channel, queue))
    .then((channel) => (channel as amqplib.Channel).consume(queue, callback, { noAck: true }))
    .catch(err => console.log(err));
}