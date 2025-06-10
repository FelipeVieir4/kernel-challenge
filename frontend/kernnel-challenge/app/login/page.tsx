'use client';
import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import ButtonLink from '@/components/btn-component';

export default function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const router = useRouter();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError('');
        // Simulação de autenticação
        if (email === 'admin@example.com' && password === 'password') {
            alert('Login realizado com sucesso!');
            // Redirecionar ou atualizar estado de autenticação aqui
        } else {
            setError('E-mail ou senha inválidos.');
        }
    };

    const handleBackHome = () => {
        router.push('/');
    };

    return (
        <div>
            <div className="flex-1 flex items-center justify-center p-8">
                <h1 className="text-3xl sm:text-4xl font-bold text-white text-center">Kernnel Challenge</h1>
            </div>
            <div style={{ maxWidth: 400, margin: '80px auto', padding: 24, border: '1px solid #ddd', borderRadius: 8 }}>
                <h2>Login</h2>
                <form onSubmit={handleSubmit}>
                    <div style={{ marginBottom: 16 }}>
                        <label htmlFor="email">E-mail</label>
                        <input
                            id="email"
                            type="email"
                            value={email}
                            onChange={e => setEmail(e.target.value)}
                            required
                            style={{ width: '100%', padding: 8, marginTop: 4, borderRadius: 4, border: '1px solid #ccc', backgroundColor: '#393e40' }}
                        />
                    </div>
                    <div style={{ marginBottom: 16 }}>
                        <label htmlFor="password">Senha</label>
                        <input
                            id="password"
                            type="password"
                            value={password}
                            onChange={e => setPassword(e.target.value)}
                            required
                            style={{ width: '100%', padding: 8, marginTop: 4, borderRadius: 4, border: '1px solid #ccc',backgroundColor: '#393e40' }}
                        />
                    </div>
                    {error && <div style={{ color: 'red', marginBottom: 16 }}>{error}</div>}
                    <div className="w-full flex flex-col mb-4">
                        <ButtonLink href="/dashboard" className="bg-white text-[#18181b] hover:bg-[#e5e5e5] w-full ">
                            Entrar
                        </ButtonLink>
                    </div>
                    <div className="w-full flex flex-col">
                        <ButtonLink href="/" className="border border-white text-white hover:bg-[#232329]">
                            Voltar
                        </ButtonLink>
                    </div>
                </form>
            </div>
        </div>
    );
}