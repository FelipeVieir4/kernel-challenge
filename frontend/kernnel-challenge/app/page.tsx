import ButtonLink from "@/components/btn-component";


export default function Home() {
  return (
    <div className="min-h-screen bg-[#18181b] flex items-center justify-center px-4">
      <div className="w-full max-w-2xl flex flex-col sm:flex-row bg-[#232329] rounded-xl shadow-lg overflow-hidden">
        {/* Lado esquerdo: Título */}
        <div className="flex-1 flex items-center justify-center p-8">
          <h1 className="text-3xl sm:text-4xl font-bold text-white text-center">Kernnel Challenge</h1>
        </div>
        {/* Lado direito: Botões */}
        <div className="flex flex-col gap-4 justify-center items-center bg-[#1a1a1e] p-8 sm:w-64">
          <ButtonLink href="/login" className="bg-white text-[#18181b] hover:bg-[#e5e5e5]">
            Login
          </ButtonLink>
          <ButtonLink href="/register" className="border border-white text-white hover:bg-[#232329]">
            Register
          </ButtonLink>
        </div>
      </div>
    </div>
  );
}