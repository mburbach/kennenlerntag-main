<?php

declare(strict_types=1);

namespace App\Repository;

use App\Model\Entity\VehicleType;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @extends ServiceEntityRepository<VehicleType>
 */
class VehicleTypeRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, VehicleType::class);
    }

    public function save(VehicleType $vehicleType): VehicleType
    {
        $this->getEntityManager()->persist($vehicleType);
        $this->getEntityManager()->flush();

        return $vehicleType;
    }
}
