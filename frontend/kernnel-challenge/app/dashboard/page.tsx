import React from "react";

const stats = [
    { label: "Usuários Ativos", value: 128 },
    { label: "Novos Cadastros", value: 24 },
    { label: "Transações Hoje", value: 312 },
    { label: "Receita (R$)", value: "5.200" },
];

export default function DashboardPage() {
    return (
        <main className="p-8 bg-gray-50 min-h-screen">
            <h1 className="text-3xl font-bold mb-8">Dashboard</h1>
            <section className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-4 gap-6 mb-10">
                {stats.map((stat) => (
                    <div
                        key={stat.label}
                        className="bg-white rounded-lg shadow p-6 flex flex-col items-center"
                    >
                        <span className="text-2xl font-semibold">{stat.value}</span>
                        <span className="text-gray-500 mt-2">{stat.label}</span>
                    </div>
                ))}
            </section>
            <section className="bg-white rounded-lg shadow p-6">
                <h2 className="text-xl font-semibold mb-4">Resumo</h2>
                <p>
                    Bem-vindo ao painel! Aqui você pode acompanhar as principais métricas do sistema.
                </p>
            </section>
        </main>
    );
}