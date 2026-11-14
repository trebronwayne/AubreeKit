import { useState } from 'react';
import { Aubree, type AubreeState } from './Aubree';

type Status = 'idle' | 'loading' | 'success' | 'error';

const MAP: Record<Status, AubreeState> = {
  idle: 'ask',
  loading: 'wait',
  success: 'happy',
  error: 'sad',
};

const TEXT: Record<Status, string> = {
  idle: 'Allow reminders?',
  loading: 'Setting things up…',
  success: "You're all set!",
  error: 'Something went wrong',
};

export default function Example() {
  const [status, setStatus] = useState<Status>('idle');

  async function run() {
    setStatus('loading');
    try {
      await fakeRequest();
      setStatus('success');
    } catch {
      setStatus('error');
    }
  }

  return (
    <div style={{ display: 'grid', placeItems: 'center', gap: 12, padding: 24 }}>
      <Aubree state={MAP[status]} size={220} />
      <h2 role="status">{TEXT[status]}</h2>
      <button onClick={run} disabled={status === 'loading'}>
        {status === 'idle' ? 'Allow' : 'Try again'}
      </button>
    </div>
  );
}

function fakeRequest() {
  return new Promise<void>((resolve, reject) =>
    setTimeout(() => (Math.random() > 0.3 ? resolve() : reject(new Error('fail'))), 1800),
  );
}
