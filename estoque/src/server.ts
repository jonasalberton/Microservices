import Fastify from "fastify"
import cors from '@fastify/cors'
import { appRoutes } from "./routes"
import { listemToQueues } from "./estoqueService"

const PORT = 3444
const app = Fastify()

app.register(cors)
app.register(appRoutes)

app.listen({ port: PORT }).then(() => {
  listemToQueues()
  console.log(`Server running at http://localhost:${PORT}`)
})