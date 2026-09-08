import type { ReactNode } from 'react';

interface StateMessageProps {
  variant: 'loading' | 'error';
  title: string;
  children?: ReactNode;
}

export function StateMessage({ variant, title, children }: StateMessageProps) {
  return (
    <div className={`state state--${variant}`} role={variant === 'error' ? 'alert' : 'status'}>
      {variant === 'loading' && <span className="state__spinner" aria-hidden="true" />}
      <div>
        <p className="state__title">{title}</p>
        {children && <div className="state__body">{children}</div>}
      </div>
    </div>
  );
}
