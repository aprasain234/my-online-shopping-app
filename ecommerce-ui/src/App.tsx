import { useState } from 'react'
import ProductList from './components/ProductList'

function App() {
  return (
    <div className="App">
      <header style={{ padding: '20px', borderBottom: '1px solid #ccc', marginBottom: '20px' }}>
        <h1>Online Shopping App</h1>
      </header>
      <main style={{ padding: '0 20px' }}>
        <ProductList />
      </main>
    </div>
  )
}

export default App
