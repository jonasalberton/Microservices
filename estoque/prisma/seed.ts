import { PrismaClient } from "@prisma/client";
import crypto from 'crypto'

const prisma = new PrismaClient();

async function run() {

  await prisma.produto.deleteMany()

  await Promise.all([
    prisma.produto.create({
      data: {
        id: crypto.randomUUID(),
        nome: "Luminaria",
        quantidade: 10,
        created_at: new Date(),
      },
    }),
    prisma.produto.create({
      data: {
        id: crypto.randomUUID(),
        nome: "Mesa",
        quantidade: 12,
        created_at: new Date(),
      },
    }),
    prisma.produto.create({
      data: {
        id: crypto.randomUUID(),
        nome: "Monitor",
        quantidade: 15,
        created_at: new Date(),
      },
    }),
  ]);
}

run()
  .then(async () => {
    console.log('Success');
    await prisma.$disconnect();
  })
  .catch(async (e) => {
    console.error(e);
    await prisma.$disconnect();
    process.exit(1);
  });
