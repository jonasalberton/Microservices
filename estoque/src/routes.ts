import { FastifyInstance } from "fastify";
import { prisma } from "./lib/prisma";
import { z } from "zod";
import { sendToQueue} from './lib/queue';

export async function appRoutes(app: FastifyInstance) {
  app.get("/produtos", async (req, res) => {
    sendToQueue("add-user", { name: 'teste'});
    return await prisma.produto.findMany();
  });

  app.get("/produtos/:id", async (req, res) => {
    const { id } = req.params as any;
    return await prisma.produto.findUnique({ where: { id } });
  });

  app.post("/produtos", async (req, res) => {
    const createProdutoBody = z.object({
      nome: z.string(),
      quantidade: z.number(),
    });

    const { nome, quantidade } = createProdutoBody.parse(req.body);

    const produto = await prisma.produto.create({
      data: { nome, quantidade, created_at: new Date() },
    });

    return produto;
  });

  app.delete("/produtos/:id", async (req, res) => {
    const { id } = req.params as any;
    console.log("id", id);

    return await prisma.produto.delete({ where: { id } });
  });
}
