

import Link from "next/link";

function ButtonLink({ href, children, className }: { href: string; children: React.ReactNode; className?: string }) {
  return (
    <Link href={href} className={`w-full text-center py-2 rounded-md font-semibold transition-colors ${className}`}>
      {children}
    </Link>
  );
}
export default ButtonLink;