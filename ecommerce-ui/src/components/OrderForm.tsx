import { useState } from 'react';

interface OrderFormProps {
  productId: string;
  price: number;
}

const OrderForm = ({ productId, price }: OrderFormProps) => {
  const [status, setStatus] = useState<'idle' | 'processing' | 'success' | 'error'>('idle');

  const handleBuyNow = async () => {
    setStatus('processing');
    try {
      const response = await fetch('http://localhost:8080/api/v1/orders', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          productId: productId,
          quantity: 1,
          totalPrice: price,
        }),
      });

      if (response.status === 201) {
        setStatus('success');
        setTimeout(() => setStatus('idle'), 3000);
      } else {
        setStatus('error');
        setTimeout(() => setStatus('idle'), 3000);
      }
    } catch (error) {
      console.error('Order submission failed:', error);
      setStatus('error');
      setTimeout(() => setStatus('idle'), 3000);
    }
  };

  const getButtonText = () => {
    switch (status) {
      case 'processing':
        return 'Processing...';
      case 'success':
        return 'Order Submitted Successfully!';
      case 'error':
        return 'Error! Try Again';
      default:
        return 'Buy Now';
    }
  };

  return (
    <button
      className={`buy-button ${status}`}
      onClick={handleBuyNow}
      disabled={status !== 'idle'}
    >
      {getButtonText()}
    </button>
  );
};

export default OrderForm;
