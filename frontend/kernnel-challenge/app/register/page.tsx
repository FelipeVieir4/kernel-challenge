'use client';
import React, { useEffect, useReducer, useState } from 'react';
import { useRouter } from 'next/navigation';
import ButtonLink from '@/components/btn-component';
import axios from 'axios';


export default function RegisterPage() {
    const [form, setForm] = useState({ name: '', email: '', password: '' });
    const [error, setError] = useState('');
    const router = useRouter();

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { id, value } = e.target;
        setForm(prev => ({ ...prev, [id]: value }));
    };

    useEffect(() => {
        console.log('Form state:', form);
    }, [form, error]);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setError('');
        if (!form.name || !form.email || !form.password) {
            setError('Preencha todos os campos.');
            return;
        }


        try {
           
            const response = await axios.post(
                `${process.env.NEXT_PUBLIC_API_URL}/auth/register`,
                {
                    name: form.name,
                    email: form.email,
                    password: form.password,
                }
            );

            if (response.status !== 201) {
                setError('Erro ao registrar. Tente novamente.');
                return;
            }

            console.log('Registro realizado com sucesso:', response.data);

            setForm({ name: '', email: '', password: '' });

        } catch (err: any) {
            console.log(err)
            setError(err.response?.data?.message || 'Erro ao registrar. Tente novamente.');
            return;
        }

        alert('Registro realizado com sucesso!');
        router.push('/dashboard');
    };

    return (
        <>
            <div className="flex-1 flex items-center justify-center p-8">
                <h1 className="text-3xl sm:text-4xl font-bold text-white text-center">Kernnel Challenge</h1>
            </div>
            <div style={{ maxWidth: 400, margin: '80px auto', padding: 24, border: '1px solid #ddd', borderRadius: 8 }}>
                <h2>Registro</h2>
                <form onSubmit={handleSubmit}>
                    <div style={{ marginBottom: 16 }}>
                        <label htmlFor="name">Nome</label>
                        <input
                            id="name"
                            type="text"
                            value={form.name}
                            onChange={handleChange}
                            required
                            style={{ width: '100%', padding: 8, marginTop: 4, borderRadius: 4, border: '1px solid #ccc', backgroundColor: '#393e40' }}
                        />
                    </div>
                    <div style={{ marginBottom: 16 }}>
                        <label htmlFor="email">E-mail</label>
                        <input
                            id="email"
                            type="email"
                            value={form.email}
                            onChange={handleChange}
                            required
                            style={{ width: '100%', padding: 8, marginTop: 4, borderRadius: 4, border: '1px solid #ccc', backgroundColor: '#393e40' }}
                        />
                    </div>
                    <div style={{ marginBottom: 16 }}>
                        <label htmlFor="password">Senha</label>
                        <input
                            id="password"
                            type="password"
                            value={form.password}
                            onChange={handleChange}
                            required
                            style={{ width: '100%', padding: 8, marginTop: 4, borderRadius: 4, border: '1px solid #ccc', backgroundColor: '#393e40' }}
                        />
                    </div>
                    {error && <div style={{ color: 'red', marginBottom: 16 }}>{error}</div>}
                    <div className="w-full flex flex-col mb-4">
                        <button
                            onClick={handleSubmit}
                            className="bg-white text-[#18181b] hover:bg-[#e5e5e5] w-full"
                            style={{ padding: 8, borderRadius: 4, border: 'none', cursor: 'pointer', fontWeight: 500 }}
                        >
                            Registrar
                        </button>
                    </div>
                    <div className="w-full flex flex-col">
                        <ButtonLink href="/" className="border border-white text-white hover:bg-[#232329]">
                            Voltar
                        </ButtonLink>
                    </div>
                </form>
            </div>
        </>
    );
}
