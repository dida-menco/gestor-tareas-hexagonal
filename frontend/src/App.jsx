import { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

const API_URL = 'http://localhost:8080/api/tareas';

function App() {
  const [tareas, setTareas] = useState([]);
  const [titulo, setTitulo] = useState('');

  useEffect(() => {
    cargarTareas();
  }, []);

  async function cargarTareas() {
    try {
      const res = await axios.get(API_URL);
      setTareas(res.data);
    } catch (err) {
      console.error('Error al cargar tareas:', err);
    }
  }

  const agregarTarea = async (e) => {
    e.preventDefault();
    if (!titulo.trim()) return;
    try {
      await axios.post(API_URL, { titulo });
      setTitulo('');
      cargarTareas();
    } catch (err) {
      console.error('Error al crear tarea:', err);
    }
  };

  const completarTarea = async (id) => {
    try {
      await axios.patch(`${API_URL}/${id}/completar`);
      cargarTareas();
    } catch (err) {
      console.error('Error al completar tarea:', err);
    }
  };

  const eliminarTarea = async (id) => {
    try {
      await axios.delete(`${API_URL}/${id}`);
      cargarTareas();
    } catch (err) {
      console.error('Error al eliminar tarea:', err);
    }
  };

  return (
    <div style={{ padding: '20px', maxWidth: '500px', margin: '0 auto', fontFamily: 'sans-serif' }}>
      <h1>Gestor de Tareas Hexagonal</h1>
      <form onSubmit={agregarTarea} style={{ display: 'flex', gap: '8px', marginBottom: '16px' }}>
        <input
          value={titulo}
          onChange={(e) => setTitulo(e.target.value)}
          placeholder="Nueva tarea..."
          style={{ flex: 1, padding: '8px' }}
        />
        <button type="submit" style={{ padding: '8px 16px' }}>Agregar</button>
      </form>
      <ul style={{ listStyle: 'none', padding: 0 }}>
        {tareas.map((t) => (
          <li key={t.id} style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '8px', alignItems: 'center' }}>
            <span style={{ textDecoration: t.completada ? 'line-through' : 'none' }}>{t.titulo}</span>
            <div style={{ display: 'flex', gap: '4px' }}>
              {!t.completada && <button onClick={() => completarTarea(t.id)}>Completar</button>}
              <button onClick={() => eliminarTarea(t.id)}>🗑</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;